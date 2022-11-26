import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './producto-seleccionado.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoSeleccionadoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const productoSeleccionadoEntity = useAppSelector(state => state.productoSeleccionado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productoSeleccionadoDetailsHeading">
          <Translate contentKey="carroDeComprasApp.productoSeleccionado.detail.title">ProductoSeleccionado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productoSeleccionadoEntity.id}</dd>
          <dt>
            <span id="producto">
              <Translate contentKey="carroDeComprasApp.productoSeleccionado.producto">Producto</Translate>
            </span>
          </dt>
          <dd>{productoSeleccionadoEntity.producto}</dd>
          <dt>
            <span id="precio">
              <Translate contentKey="carroDeComprasApp.productoSeleccionado.precio">Precio</Translate>
            </span>
          </dt>
          <dd>{productoSeleccionadoEntity.precio}</dd>
          <dt>
            <span id="cantidad">
              <Translate contentKey="carroDeComprasApp.productoSeleccionado.cantidad">Cantidad</Translate>
            </span>
          </dt>
          <dd>{productoSeleccionadoEntity.cantidad}</dd>
          <dt>
            <Translate contentKey="carroDeComprasApp.productoSeleccionado.carro">Carro</Translate>
          </dt>
          <dd>{productoSeleccionadoEntity.carro ? productoSeleccionadoEntity.carro.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/producto-seleccionado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/producto-seleccionado/${productoSeleccionadoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductoSeleccionadoDetail;
