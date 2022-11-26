import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './producto-comprado.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoCompradoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const productoCompradoEntity = useAppSelector(state => state.productoComprado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productoCompradoDetailsHeading">
          <Translate contentKey="carroDeComprasApp.productoComprado.detail.title">ProductoComprado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productoCompradoEntity.id}</dd>
          <dt>
            <span id="producto">
              <Translate contentKey="carroDeComprasApp.productoComprado.producto">Producto</Translate>
            </span>
          </dt>
          <dd>{productoCompradoEntity.producto}</dd>
          <dt>
            <span id="precio">
              <Translate contentKey="carroDeComprasApp.productoComprado.precio">Precio</Translate>
            </span>
          </dt>
          <dd>{productoCompradoEntity.precio}</dd>
          <dt>
            <span id="cantidad">
              <Translate contentKey="carroDeComprasApp.productoComprado.cantidad">Cantidad</Translate>
            </span>
          </dt>
          <dd>{productoCompradoEntity.cantidad}</dd>
          <dt>
            <Translate contentKey="carroDeComprasApp.productoComprado.venta">Venta</Translate>
          </dt>
          <dd>{productoCompradoEntity.venta ? productoCompradoEntity.venta.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/producto-comprado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/producto-comprado/${productoCompradoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductoCompradoDetail;
