import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './address.reducer';
import { IAddress } from 'app/shared/model/address.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddressDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class AddressDetail extends React.Component<IAddressDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { addressEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Address [<b>{addressEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentId">Student Id</span>
            </dt>
            <dd>{addressEntity.studentId}</dd>
            <dt>
              <span id="addressLineOne">Address Line One</span>
            </dt>
            <dd>{addressEntity.addressLineOne}</dd>
            <dt>
              <span id="addressLineTwo">Address Line Two</span>
            </dt>
            <dd>{addressEntity.addressLineTwo}</dd>
            <dt>
              <span id="city">City</span>
            </dt>
            <dd>{addressEntity.city}</dd>
            <dt>
              <span id="region">Region</span>
            </dt>
            <dd>{addressEntity.region}</dd>
            <dt>
              <span id="postalCode">Postal Code</span>
            </dt>
            <dd>{addressEntity.postalCode}</dd>
          </dl>
          <Button tag={Link} to="/entity/address" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/address/${addressEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ address }: IRootState) => ({
  addressEntity: address.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AddressDetail);
