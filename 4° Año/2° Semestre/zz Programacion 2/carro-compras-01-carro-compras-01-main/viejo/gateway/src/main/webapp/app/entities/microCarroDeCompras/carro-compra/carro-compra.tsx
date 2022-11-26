import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './carro-compra.reducer';
import { ICarroCompra } from 'app/shared/model/microCarroDeCompras/carro-compra.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const CarroCompra = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const carroCompraList = useAppSelector(state => state.carroCompra.entities);
  const loading = useAppSelector(state => state.carroCompra.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="carro-compra-heading" data-cy="CarroCompraHeading">
        <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.home.title">Carro Compras</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.home.createLabel">Create new Carro Compra</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {carroCompraList && carroCompraList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.cliente">Cliente</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.total">Total</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.finalizado">Finalizado</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.vendido">Vendido</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {carroCompraList.map((carroCompra, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${carroCompra.id}`} color="link" size="sm">
                      {carroCompra.id}
                    </Button>
                  </td>
                  <td>{carroCompra.cliente}</td>
                  <td>{carroCompra.total}</td>
                  <td>{carroCompra.finalizado ? 'true' : 'false'}</td>
                  <td>{carroCompra.vendido ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${carroCompra.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${carroCompra.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${carroCompra.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="gatewayApp.microCarroDeComprasCarroCompra.home.notFound">No Carro Compras found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CarroCompra;
