import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPais } from 'app/shared/model/pais.model';
import { getEntities as getPais } from 'app/entities/pais/pais.reducer';
import { getEntity, updateEntity, createEntity, reset } from './provincia.reducer';
import { IProvincia } from 'app/shared/model/provincia.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProvinciaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProvinciaUpdate = (props: IProvinciaUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { provinciaEntity, pais, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/provincia' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getPais();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...provinciaEntity,
        ...values,
        pais: pais.find(it => it.id.toString() === values.paisId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="servicio1App.provincia.home.createOrEditLabel" data-cy="ProvinciaCreateUpdateHeading">
            <Translate contentKey="servicio1App.provincia.home.createOrEditLabel">Create or edit a Provincia</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : provinciaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="provincia-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="provincia-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nombreLabel" for="provincia-nombre">
                  <Translate contentKey="servicio1App.provincia.nombre">Nombre</Translate>
                </Label>
                <AvField id="provincia-nombre" data-cy="nombre" type="text" name="nombre" />
              </AvGroup>
              <AvGroup>
                <Label id="codigoPostalLabel" for="provincia-codigoPostal">
                  <Translate contentKey="servicio1App.provincia.codigoPostal">Codigo Postal</Translate>
                </Label>
                <AvField id="provincia-codigoPostal" data-cy="codigoPostal" type="text" name="codigoPostal" />
              </AvGroup>
              <AvGroup>
                <Label for="provincia-pais">
                  <Translate contentKey="servicio1App.provincia.pais">Pais</Translate>
                </Label>
                <AvInput id="provincia-pais" data-cy="pais" type="select" className="form-control" name="paisId">
                  <option value="" key="0" />
                  {pais
                    ? pais.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.nombre}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/provincia" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  pais: storeState.pais.entities,
  provinciaEntity: storeState.provincia.entity,
  loading: storeState.provincia.loading,
  updating: storeState.provincia.updating,
  updateSuccess: storeState.provincia.updateSuccess,
});

const mapDispatchToProps = {
  getPais,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProvinciaUpdate);
