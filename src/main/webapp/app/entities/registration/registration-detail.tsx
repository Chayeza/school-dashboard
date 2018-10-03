import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './registration.reducer';
import { IRegistration } from 'app/shared/model/registration.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRegistrationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class RegistrationDetail extends React.Component<IRegistrationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { registrationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Registration [<b>{registrationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentId">Student Id</span>
            </dt>
            <dd>{registrationEntity.studentId}</dd>
            <dt>
              <span id="courseId">Course Id</span>
            </dt>
            <dd>{registrationEntity.courseId}</dd>
            <dt>
              <span id="credits">Credits</span>
            </dt>
            <dd>{registrationEntity.credits}</dd>
            <dt>
              <span id="date">Date</span>
            </dt>
            <dd>
              <TextFormat value={registrationEntity.date} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/registration" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/registration/${registrationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ registration }: IRootState) => ({
  registrationEntity: registration.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RegistrationDetail);
