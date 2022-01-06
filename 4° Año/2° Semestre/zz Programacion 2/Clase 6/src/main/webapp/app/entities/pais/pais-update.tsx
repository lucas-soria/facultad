import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pais.reducer';
import { IPais } from 'app/shared/model/pais.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaisUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaisUpdate = (props: IPaisUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { paisEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/pais');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...paisEntity,
        ...values,
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
          <h2 id="servicio1App.pais.home.createOrEditLabel" data-cy="PaisCreateUpdateHeading">
            <Translate contentKey="servicio1App.pais.home.createOrEditLabel">Create or edit a Pais</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : paisEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="pais-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="pais-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nombreLabel" for="pais-nombre">
                  <Translate contentKey="servicio1App.pais.nombre">Nombre</Translate>
                </Label>
                <AvField
                  id="pais-nombre"
                  data-cy="nombre"
                  type="text"
                  name="nombre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="continenteLabel" for="pais-continente">
                  <Translate contentKey="servicio1App.pais.continente">Continente</Translate>
                </Label>
                <AvField
                  id="pais-continente"
                  data-cy="continente"
                  type="text"
                  name="continente"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/pais" replace color="info">
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
  paisEntity: storeState.pais.entity,
  loading: storeState.pais.loading,
  updating: storeState.pais.updating,
  updateSuccess: storeState.pais.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaisUpdate);
