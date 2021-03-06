import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './coarse.reducer';
import { ICoarse } from 'app/shared/model/coarse.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICoarseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ICoarseUpdateState {
  isNew: boolean;
}

export class CoarseUpdate extends React.Component<ICoarseUpdateProps, ICoarseUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { coarseEntity } = this.props;
      const entity = {
        ...coarseEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/coarse');
  };

  render() {
    const { coarseEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="schooldashboardApp.coarse.home.createOrEditLabel">Create or edit a Coarse</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : coarseEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="coarse-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="courseIdLabel" for="courseId">
                    Course Id
                  </Label>
                  <AvField
                    id="coarse-courseId"
                    type="number"
                    className="form-control"
                    name="courseId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    Name
                  </Label>
                  <AvField
                    id="coarse-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="durationLabel" for="duration">
                    Duration
                  </Label>
                  <AvField
                    id="coarse-duration"
                    type="number"
                    className="form-control"
                    name="duration"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="costLabel" for="cost">
                    Cost
                  </Label>
                  <AvField id="coarse-cost" type="number" className="form-control" name="cost" />
                </AvGroup>
                <AvGroup>
                  <Label id="levelLabel">Level</Label>
                  <AvInput
                    id="coarse-level"
                    type="select"
                    className="form-control"
                    name="level"
                    value={(!isNew && coarseEntity.level) || 'ONE'}
                  >
                    <option value="ONE">ONE</option>
                    <option value="TWO">TWO</option>
                    <option value="THREE">THREE</option>
                    <option value="PG">PG</option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/coarse" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  coarseEntity: storeState.coarse.entity,
  loading: storeState.coarse.loading,
  updating: storeState.coarse.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CoarseUpdate);
