import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './student.reducer';
import { IStudent } from 'app/shared/model/student.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class StudentDetail extends React.Component<IStudentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { studentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Student [<b>{studentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentId">Student Id</span>
            </dt>
            <dd>{studentEntity.studentId}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{studentEntity.name}</dd>
            <dt>
              <span id="surname">Surname</span>
            </dt>
            <dd>{studentEntity.surname}</dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{studentEntity.sex}</dd>
            <dt>
              <span id="idNumber">Id Number</span>
            </dt>
            <dd>{studentEntity.idNumber}</dd>
            <dt>
              <span id="race">Race</span>
            </dt>
            <dd>{studentEntity.race}</dd>
            <dt>
              <span id="nationality">Nationality</span>
            </dt>
            <dd>{studentEntity.nationality}</dd>
            <dt>
              <span id="contact">Contact</span>
            </dt>
            <dd>{studentEntity.contact}</dd>
          </dl>
          <Button tag={Link} to="/entity/student" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/student/${studentEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ student }: IRootState) => ({
  studentEntity: student.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentDetail);
