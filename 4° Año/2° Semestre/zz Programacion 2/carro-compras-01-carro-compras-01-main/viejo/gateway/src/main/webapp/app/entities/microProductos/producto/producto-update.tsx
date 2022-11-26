import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDistribuidor } from 'app/shared/model/microProductos/distribuidor.model';
import { getEntities as getDistribuidors } from 'app/entities/microProductos/distribuidor/distribuidor.reducer';
import { getEntity, updateEntity, createEntity, reset } from './producto.reducer';
import { IProducto } from 'app/shared/model/microProductos/producto.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const distribuidors = useAppSelector(state => state.distribuidor.entities);
  const productoEntity = useAppSelector(state => state.producto.entity);
  const loading = useAppSelector(state => state.producto.loading);
  const updating = useAppSelector(state => state.producto.updating);
  const updateSuccess = useAppSelector(state => state.producto.updateSuccess);

  const handleClose = () => {
    props.history.push('/producto');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getDistribuidors({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...productoEntity,
      ...values,
      distribuidor: distribuidors.find(it => it.id.toString() === values.distribuidorId.toString()),
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
          ...productoEntity,
          distribuidorId: productoEntity?.distribuidor?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gatewayApp.microProductosProducto.home.createOrEditLabel" data-cy="ProductoCreateUpdateHeading">
            <Translate contentKey="gatewayApp.microProductosProducto.home.createOrEditLabel">Create or edit a Producto</Translate>
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
                  id="producto-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gatewayApp.microProductosProducto.nombre')}
                id="producto-nombre"
                name="nombre"
                data-cy="nombre"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microProductosProducto.descripcion')}
                id="producto-descripcion"
                name="descripcion"
                data-cy="descripcion"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microProductosProducto.precio')}
                id="producto-precio"
                name="precio"
                data-cy="precio"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microProductosProducto.cantidadVendidos')}
                id="producto-cantidadVendidos"
                name="cantidadVendidos"
                data-cy="cantidadVendidos"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microProductosProducto.habilitado')}
                id="producto-habilitado"
                name="habilitado"
                data-cy="habilitado"
                check
                type="checkbox"
              />
              <ValidatedField
                id="producto-distribuidor"
                name="distribuidorId"
                data-cy="distribuidor"
                label={translate('gatewayApp.microProductosProducto.distribuidor')}
                type="select"
              >
                <option value="" key="0" />
                {distribuidors
                  ? distribuidors.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/producto" replace color="info">
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

export default ProductoUpdate;
