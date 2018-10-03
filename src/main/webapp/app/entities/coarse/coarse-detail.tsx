import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './coarse.reducer';
import { ICoarse } from 'app/shared/model/coarse.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICoarseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class CoarseDetail extends React.Component<ICoarseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { coarseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Coarse [<b>{coarseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="courseId">Course Id</span>
            </dt>
            <dd>{coarseEntity.courseId}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{coarseEntity.name}</dd>
            <dt>
              <span id="duration">Duration</span>
            </dt>
            <dd>{coarseEntity.duration}</dd>
            <dt>
              <span id="cost">Cost</span>
            </dt>
            <dd>{coarseEntity.cost}</dd>
            <dt>
              <span id="level">Level</span>
            </dt>
            <dd>{coarseEntity.level}</dd>
          </dl>
          <Button tag={Link} to="/entity/coarse" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/coarse/${coarseEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ coarse }: IRootState) => ({
  coarseEntity: coarse.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CoarseDetail);
