import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './distribuidor.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DistribuidorDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const distribuidorEntity = useAppSelector(state => state.distribuidor.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="distribuidorDetailsHeading">
          <Translate contentKey="gatewayApp.microProductosDistribuidor.detail.title">Distribuidor</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{distribuidorEntity.id}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="gatewayApp.microProductosDistribuidor.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{distribuidorEntity.nombre}</dd>
          <dt>
            <span id="descripcion">
              <Translate contentKey="gatewayApp.microProductosDistribuidor.descripcion">Descripcion</Translate>
            </span>
          </dt>
          <dd>{distribuidorEntity.descripcion}</dd>
          <dt>
            <span id="habilitado">
              <Translate contentKey="gatewayApp.microProductosDistribuidor.habilitado">Habilitado</Translate>
            </span>
          </dt>
          <dd>{distribuidorEntity.habilitado ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/distribuidor" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/distribuidor/${distribuidorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DistribuidorDetail;
