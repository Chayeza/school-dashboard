import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <DropdownItem tag={Link} to="/entity/student">
      <FontAwesomeIcon icon="asterisk" />
      &nbsp;Student
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/address">
      <FontAwesomeIcon icon="asterisk" />
      &nbsp;Address
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/coarse">
      <FontAwesomeIcon icon="asterisk" />
      &nbsp;Coarse
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/registration">
      <FontAwesomeIcon icon="asterisk" />
      &nbsp;Registration
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
