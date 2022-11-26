import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './producto.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const productoEntity = useAppSelector(state => state.producto.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productoDetailsHeading">
          <Translate contentKey="productosApp.producto.detail.title">Producto</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productoEntity.id}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="productosApp.producto.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{productoEntity.nombre}</dd>
          <dt>
            <span id="descripcion">
              <Translate contentKey="productosApp.producto.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{productoEntity.descripcion}</dd>
          <dt>
            <span id="precio">
              <Translate contentKey="productosApp.producto.precio">Precio</Translate>
            </span>
          </dt>
          <dd>{productoEntity.precio}</dd>
          <dt>
            <span id="cantidadVendidos">
              <Translate contentKey="productosApp.producto.cantidadVendidos">Cantidad Vendidos</Translate>
            </span>
          </dt>
          <dd>{productoEntity.cantidadVendidos}</dd>
          <dt>
            <span id="habilitado">
              <Translate contentKey="productosApp.producto.habilitado">Habilitado</Translate>
            </span>
          </dt>
          <dd>{productoEntity.habilitado ? 'true' : 'false'}</dd>
          <dt>
            <span id="imagen">
              <Translate contentKey="productosApp.producto.imagen">Imagen</Translate>
            </span>
          </dt>
          <dd>{productoEntity.imagen}</dd>
          <dt>
            <Translate contentKey="productosApp.producto.distribuidor">Distribuidor</Translate>
          </dt>
          <dd>{productoEntity.distribuidor ? productoEntity.distribuidor.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/producto" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/producto/${productoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductoDetail;
