import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pais.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaisDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaisDetail = (props: IPaisDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { paisEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paisDetailsHeading">
          <Translate contentKey="servicio1App.pais.detail.title">Pais</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paisEntity.id}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="servicio1App.pais.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{paisEntity.nombre}</dd>
          <dt>
            <span id="continente">
              <Translate contentKey="servicio1App.pais.continente">Continente</Translate>
            </span>
          </dt>
          <dd>{paisEntity.continente}</dd>
        </dl>
        <Button tag={Link} to="/pais" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/pais/${paisEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ pais }: IRootState) => ({
  paisEntity: pais.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaisDetail);
