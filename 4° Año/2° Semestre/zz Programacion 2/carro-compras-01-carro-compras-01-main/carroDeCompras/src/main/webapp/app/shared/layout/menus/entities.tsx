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
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/carro-compra">
      <Translate contentKey="global.menu.entities.carroCompra" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/producto-comprado">
      <Translate contentKey="global.menu.entities.productoComprado" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/venta">
      <Translate contentKey="global.menu.entities.venta" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/producto-seleccionado">
      <Translate contentKey="global.menu.entities.productoSeleccionado" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
