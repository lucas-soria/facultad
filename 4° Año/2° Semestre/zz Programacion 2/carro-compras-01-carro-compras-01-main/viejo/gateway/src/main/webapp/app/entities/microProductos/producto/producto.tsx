import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './producto.reducer';
import { IProducto } from 'app/shared/model/microProductos/producto.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Producto = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const productoList = useAppSelector(state => state.producto.entities);
  const loading = useAppSelector(state => state.producto.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="producto-heading" data-cy="ProductoHeading">
        <Translate contentKey="gatewayApp.microProductosProducto.home.title">Productos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gatewayApp.microProductosProducto.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gatewayApp.microProductosProducto.home.createLabel">Create new Producto</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {productoList && productoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.nombre">Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.precio">Precio</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.cantidadVendidos">Cantidad Vendidos</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.habilitado">Habilitado</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microProductosProducto.distribuidor">Distribuidor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productoList.map((producto, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${producto.id}`} color="link" size="sm">
                      {producto.id}
                    </Button>
                  </td>
                  <td>{producto.nombre}</td>
                  <td>{producto.descripcion}</td>
                  <td>{producto.precio}</td>
                  <td>{producto.cantidadVendidos}</td>
                  <td>{producto.habilitado ? 'true' : 'false'}</td>
                  <td>
                    {producto.distribuidor ? <Link to={`distribuidor/${producto.distribuidor.id}`}>{producto.distribuidor.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${producto.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${producto.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${producto.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="gatewayApp.microProductosProducto.home.notFound">No Productos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Producto;
