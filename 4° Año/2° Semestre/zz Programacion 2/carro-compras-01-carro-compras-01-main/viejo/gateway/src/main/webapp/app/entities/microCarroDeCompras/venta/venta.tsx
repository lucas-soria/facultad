import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './venta.reducer';
import { IVenta } from 'app/shared/model/microCarroDeCompras/venta.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Venta = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const ventaList = useAppSelector(state => state.venta.entities);
  const loading = useAppSelector(state => state.venta.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="venta-heading" data-cy="VentaHeading">
        <Translate contentKey="gatewayApp.microCarroDeComprasVenta.home.title">Ventas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gatewayApp.microCarroDeComprasVenta.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gatewayApp.microCarroDeComprasVenta.home.createLabel">Create new Venta</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {ventaList && ventaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasVenta.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasVenta.cliente">Cliente</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasVenta.fecha">Fecha</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasVenta.total">Total</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasVenta.carro">Carro</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ventaList.map((venta, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${venta.id}`} color="link" size="sm">
                      {venta.id}
                    </Button>
                  </td>
                  <td>{venta.cliente}</td>
                  <td>{venta.fecha ? <TextFormat type="date" value={venta.fecha} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{venta.total}</td>
                  <td>{venta.carro ? <Link to={`carro-compra/${venta.carro.id}`}>{venta.carro.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${venta.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${venta.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${venta.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gatewayApp.microCarroDeComprasVenta.home.notFound">No Ventas found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Venta;
