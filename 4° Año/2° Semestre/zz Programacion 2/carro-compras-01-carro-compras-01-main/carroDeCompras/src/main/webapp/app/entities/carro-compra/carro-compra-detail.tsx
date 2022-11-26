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
          <Translate contentKey="carroDeComprasApp.carroCompra.detail.title">CarroCompra</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.id}</dd>
          <dt>
            <span id="total">
              <Translate contentKey="carroDeComprasApp.carroCompra.total">Total</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.total}</dd>
          <dt>
            <span id="finalizado">
              <Translate contentKey="carroDeComprasApp.carroCompra.finalizado">Finalizado</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.finalizado ? 'true' : 'false'}</dd>
          <dt>
            <span id="vendido">
              <Translate contentKey="carroDeComprasApp.carroCompra.vendido">Vendido</Translate>
            </span>
          </dt>
          <dd>{carroCompraEntity.vendido ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="carroDeComprasApp.carroCompra.cliente">Cliente</Translate>
          </dt>
          <dd>{carroCompraEntity.cliente ? carroCompraEntity.cliente.id : ''}</dd>
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
