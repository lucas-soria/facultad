import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IVenta } from 'app/shared/model/microCarroDeCompras/venta.model';
import { getEntities as getVentas } from 'app/entities/microCarroDeCompras/venta/venta.reducer';
import { getEntity, updateEntity, createEntity, reset } from './producto-comprado.reducer';
import { IProductoComprado } from 'app/shared/model/microCarroDeCompras/producto-comprado.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoCompradoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const ventas = useAppSelector(state => state.venta.entities);
  const productoCompradoEntity = useAppSelector(state => state.productoComprado.entity);
  const loading = useAppSelector(state => state.productoComprado.loading);
  const updating = useAppSelector(state => state.productoComprado.updating);
  const updateSuccess = useAppSelector(state => state.productoComprado.updateSuccess);

  const handleClose = () => {
    props.history.push('/producto-comprado');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getVentas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...productoCompradoEntity,
      ...values,
      venta: ventas.find(it => it.id.toString() === values.ventaId.toString()),
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
          ...productoCompradoEntity,
          ventaId: productoCompradoEntity?.venta?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gatewayApp.microCarroDeComprasProductoComprado.home.createOrEditLabel" data-cy="ProductoCompradoCreateUpdateHeading">
            <Translate contentKey="gatewayApp.microCarroDeComprasProductoComprado.home.createOrEditLabel">
              Create or edit a ProductoComprado
            </Translate>
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
                  id="producto-comprado-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasProductoComprado.producto')}
                id="producto-comprado-producto"
                name="producto"
                data-cy="producto"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasProductoComprado.precio')}
                id="producto-comprado-precio"
                name="precio"
                data-cy="precio"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasProductoComprado.cantidad')}
                id="producto-comprado-cantidad"
                name="cantidad"
                data-cy="cantidad"
                type="text"
              />
              <ValidatedField
                id="producto-comprado-venta"
                name="ventaId"
                data-cy="venta"
                label={translate('gatewayApp.microCarroDeComprasProductoComprado.venta')}
                type="select"
              >
                <option value="" key="0" />
                {ventas
                  ? ventas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/producto-comprado" replace color="info">
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

export default ProductoCompradoUpdate;
