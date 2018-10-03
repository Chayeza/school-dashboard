import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICoarse, defaultValue } from 'app/shared/model/coarse.model';

export const ACTION_TYPES = {
  FETCH_COARSE_LIST: 'coarse/FETCH_COARSE_LIST',
  FETCH_COARSE: 'coarse/FETCH_COARSE',
  CREATE_COARSE: 'coarse/CREATE_COARSE',
  UPDATE_COARSE: 'coarse/UPDATE_COARSE',
  DELETE_COARSE: 'coarse/DELETE_COARSE',
  RESET: 'coarse/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICoarse>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CoarseState = Readonly<typeof initialState>;

// Reducer

export default (state: CoarseState = initialState, action): CoarseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COARSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COARSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COARSE):
    case REQUEST(ACTION_TYPES.UPDATE_COARSE):
    case REQUEST(ACTION_TYPES.DELETE_COARSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_COARSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COARSE):
    case FAILURE(ACTION_TYPES.CREATE_COARSE):
    case FAILURE(ACTION_TYPES.UPDATE_COARSE):
    case FAILURE(ACTION_TYPES.DELETE_COARSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_COARSE_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_COARSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COARSE):
    case SUCCESS(ACTION_TYPES.UPDATE_COARSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COARSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/coarses';

// Actions

export const getEntities: ICrudGetAllAction<ICoarse> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_COARSE_LIST,
    payload: axios.get<ICoarse>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICoarse> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COARSE,
    payload: axios.get<ICoarse>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICoarse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COARSE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<ICoarse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COARSE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICoarse> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COARSE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
