import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './provincia.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProvinciaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProvinciaDetail = (props: IProvinciaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { provinciaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="provinciaDetailsHeading">
          <Translate contentKey="servicio1App.provincia.detail.title">Provincia</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{provinciaEntity.id}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="servicio1App.provincia.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{provinciaEntity.nombre}</dd>
          <dt>
            <span id="codigoPostal">
              <Translate contentKey="servicio1App.provincia.codigoPostal">Codigo Postal</Translate>
            </span>
          </dt>
          <dd>{provinciaEntity.codigoPostal}</dd>
          <dt>
            <Translate contentKey="servicio1App.provincia.pais">Pais</Translate>
          </dt>
          <dd>{provinciaEntity.pais ? provinciaEntity.pais.nombre : ''}</dd>
        </dl>
        <Button tag={Link} to="/provincia" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/provincia/${provinciaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ provincia }: IRootState) => ({
  provinciaEntity: provincia.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProvinciaDetail);
