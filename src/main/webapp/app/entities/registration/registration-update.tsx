import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './registration.reducer';
import { IRegistration } from 'app/shared/model/registration.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRegistrationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IRegistrationUpdateState {
  isNew: boolean;
}

export class RegistrationUpdate extends React.Component<IRegistrationUpdateProps, IRegistrationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    values.date = new Date(values.date);

    if (errors.length === 0) {
      const { registrationEntity } = this.props;
      const entity = {
        ...registrationEntity,
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
    this.props.history.push('/entity/registration');
  };

  render() {
    const { registrationEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="schooldashboardApp.registration.home.createOrEditLabel">Create or edit a Registration</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : registrationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="registration-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentIdLabel" for="studentId">
                    Student Id
                  </Label>
                  <AvField
                    id="registration-studentId"
                    type="number"
                    className="form-control"
                    name="studentId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="courseIdLabel" for="courseId">
                    Course Id
                  </Label>
                  <AvField
                    id="registration-courseId"
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
                  <Label id="creditsLabel" for="credits">
                    Credits
                  </Label>
                  <AvField id="registration-credits" type="number" className="form-control" name="credits" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateLabel" for="date">
                    Date
                  </Label>
                  <AvInput
                    id="registration-date"
                    type="datetime-local"
                    className="form-control"
                    name="date"
                    value={isNew ? null : convertDateTimeFromServer(this.props.registrationEntity.date)}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/registration" replace color="info">
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
  registrationEntity: storeState.registration.entity,
  loading: storeState.registration.loading,
  updating: storeState.registration.updating
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
)(RegistrationUpdate);
