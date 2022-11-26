import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICarroCompra } from 'app/shared/model/microCarroDeCompras/carro-compra.model';
import { getEntities as getCarroCompras } from 'app/entities/microCarroDeCompras/carro-compra/carro-compra.reducer';
import { getEntity, updateEntity, createEntity, reset } from './venta.reducer';
import { IVenta } from 'app/shared/model/microCarroDeCompras/venta.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VentaUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const carroCompras = useAppSelector(state => state.carroCompra.entities);
  const ventaEntity = useAppSelector(state => state.venta.entity);
  const loading = useAppSelector(state => state.venta.loading);
  const updating = useAppSelector(state => state.venta.updating);
  const updateSuccess = useAppSelector(state => state.venta.updateSuccess);

  const handleClose = () => {
    props.history.push('/venta');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getCarroCompras({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...ventaEntity,
      ...values,
      carro: carroCompras.find(it => it.id.toString() === values.carroId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...ventaEntity,
          carroId: ventaEntity?.carro?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gatewayApp.microCarroDeComprasVenta.home.createOrEditLabel" data-cy="VentaCreateUpdateHeading">
            <Translate contentKey="gatewayApp.microCarroDeComprasVenta.home.createOrEditLabel">Create or edit a Venta</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="venta-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasVenta.cliente')}
                id="venta-cliente"
                name="cliente"
                data-cy="cliente"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasVenta.fecha')}
                id="venta-fecha"
                name="fecha"
                data-cy="fecha"
                type="date"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasVenta.total')}
                id="venta-total"
                name="total"
                data-cy="total"
                type="text"
              />
              <ValidatedField
                id="venta-carro"
                name="carroId"
                data-cy="carro"
                label={translate('gatewayApp.microCarroDeComprasVenta.carro')}
                type="select"
              >
                <option value="" key="0" />
                {carroCompras
                  ? carroCompras.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/venta" replace color="info">
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
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default VentaUpdate;
