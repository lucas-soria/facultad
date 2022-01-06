import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/pais">
      <Translate contentKey="global.menu.entities.pais" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/provincia">
      <Translate contentKey="global.menu.entities.provincia" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
