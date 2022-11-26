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
      <Translate contentKey="global.menu.entities.microCarroDeComprasCarroCompra" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/distribuidor">
      <Translate contentKey="global.menu.entities.microProductosDistribuidor" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/producto">
      <Translate contentKey="global.menu.entities.microProductosProducto" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/producto-comprado">
      <Translate contentKey="global.menu.entities.microCarroDeComprasProductoComprado" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/producto-seleccionado">
      <Translate contentKey="global.menu.entities.microCarroDeComprasProductoSeleccionado" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/venta">
      <Translate contentKey="global.menu.entities.microCarroDeComprasVenta" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
