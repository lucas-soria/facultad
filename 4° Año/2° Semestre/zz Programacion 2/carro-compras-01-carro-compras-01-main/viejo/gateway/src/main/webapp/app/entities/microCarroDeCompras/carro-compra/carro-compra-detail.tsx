import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './carro-compra.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CarroCompraDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const carroCompraEntity = useAppSelector(state => state.carroCompra.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carroCompraDetailsHeading">
          <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.detail.title">CarroCompra</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.id}</dd>
          <dt>
            <span id="cliente">
              <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.cliente">Cliente</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.cliente}</dd>
          <dt>
            <span id="total">
              <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.total">Total</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.total}</dd>
          <dt>
            <span id="finalizado">
              <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.finalizado">Finalizado</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.finalizado ? 'true' : 'false'}</dd>
          <dt>
            <span id="vendido">
              <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.vendido">Vendido</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.vendido ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/carro-compra" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/carro-compra/${carroCompraEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarroCompraDetail;
