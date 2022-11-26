import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './carro-compra.reducer';
import { ICarroCompra } from 'app/shared/model/microCarroDeCompras/carro-compra.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CarroCompraUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const carroCompraEntity = useAppSelector(state => state.carroCompra.entity);
  const loading = useAppSelector(state => state.carroCompra.loading);
  const updating = useAppSelector(state => state.carroCompra.updating);
  const updateSuccess = useAppSelector(state => state.carroCompra.updateSuccess);

  const handleClose = () => {
    props.history.push('/carro-compra');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...carroCompraEntity,
      ...values,
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
          ...carroCompraEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gatewayApp.microCarroDeComprasCarroCompra.home.createOrEditLabel" data-cy="CarroCompraCreateUpdateHeading">
            <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.home.createOrEditLabel">
              Create or edit a CarroCompra
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
                  id="carro-compra-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasCarroCompra.cliente')}
                id="carro-compra-cliente"
                name="cliente"
                data-cy="cliente"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasCarroCompra.total')}
                id="carro-compra-total"
                name="total"
                data-cy="total"
                type="text"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasCarroCompra.finalizado')}
                id="carro-compra-finalizado"
                name="finalizado"
                data-cy="finalizado"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('gatewayApp.microCarroDeComprasCarroCompra.vendido')}
                id="carro-compra-vendido"
                name="vendido"
                data-cy="vendido"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/carro-compra" replace color="info">
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

export default CarroCompraUpdate;
