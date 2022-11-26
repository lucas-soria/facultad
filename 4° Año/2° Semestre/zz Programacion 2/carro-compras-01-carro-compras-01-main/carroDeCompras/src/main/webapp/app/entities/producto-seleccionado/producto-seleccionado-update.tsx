import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICarroCompra } from 'app/shared/model/carro-compra.model';
import { getEntities as getCarroCompras } from 'app/entities/carro-compra/carro-compra.reducer';
import { getEntity, updateEntity, createEntity, reset } from './producto-seleccionado.reducer';
import { IProductoSeleccionado } from 'app/shared/model/producto-seleccionado.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoSeleccionadoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const carroCompras = useAppSelector(state => state.carroCompra.entities);
  const productoSeleccionadoEntity = useAppSelector(state => state.productoSeleccionado.entity);
  const loading = useAppSelector(state => state.productoSeleccionado.loading);
  const updating = useAppSelector(state => state.productoSeleccionado.updating);
  const updateSuccess = useAppSelector(state => state.productoSeleccionado.updateSuccess);
  const handleClose = () => {
    props.history.push('/producto-seleccionado');
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
      ...productoSeleccionadoEntity,
      ...values,
      carro: carroCompras.find(it => it.id.toString() === values.carro.toString()),
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
          ...productoSeleccionadoEntity,
          carro: productoSeleccionadoEntity?.carro?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="carroDeComprasApp.productoSeleccionado.home.createOrEditLabel" data-cy="ProductoSeleccionadoCreateUpdateHeading">
            <Translate contentKey="carroDeComprasApp.productoSeleccionado.home.createOrEditLabel">
              Create or edit a ProductoSeleccionado
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
                  id="producto-seleccionado-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('carroDeComprasApp.productoSeleccionado.producto')}
                id="producto-seleccionado-producto"
                name="producto"
                data-cy="producto"
                type="text"
              />
              <ValidatedField
                label={translate('carroDeComprasApp.productoSeleccionado.precio')}
                id="producto-seleccionado-precio"
                name="precio"
                data-cy="precio"
                type="text"
              />
              <ValidatedField
                label={translate('carroDeComprasApp.productoSeleccionado.cantidad')}
                id="producto-seleccionado-cantidad"
                name="cantidad"
                data-cy="cantidad"
                type="text"
              />
              <ValidatedField
                id="producto-seleccionado-carro"
                name="carro"
                data-cy="carro"
                label={translate('carroDeComprasApp.productoSeleccionado.carro')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/producto-seleccionado" replace color="info">
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

export default ProductoSeleccionadoUpdate;
