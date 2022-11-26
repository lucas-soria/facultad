import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './producto-comprado.reducer';
import { IProductoComprado } from 'app/shared/model/producto-comprado.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoComprado = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const productoCompradoList = useAppSelector(state => state.productoComprado.entities);
  const loading = useAppSelector(state => state.productoComprado.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="producto-comprado-heading" data-cy="ProductoCompradoHeading">
        <Translate contentKey="carroDeComprasApp.productoComprado.home.title">Producto Comprados</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="carroDeComprasApp.productoComprado.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="carroDeComprasApp.productoComprado.home.createLabel">Create new Producto Comprado</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {productoCompradoList && productoCompradoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="carroDeComprasApp.productoComprado.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="carroDeComprasApp.productoComprado.producto">Producto</Translate>
                </th>
                <th>
                  <Translate contentKey="carroDeComprasApp.productoComprado.precio">Precio</Translate>
                </th>
                <th>
                  <Translate contentKey="carroDeComprasApp.productoComprado.cantidad">Cantidad</Translate>
                </th>
                <th>
                  <Translate contentKey="carroDeComprasApp.productoComprado.venta">Venta</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productoCompradoList.map((productoComprado, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${productoComprado.id}`} color="link" size="sm">
                      {productoComprado.id}
                    </Button>
                  </td>
                  <td>{productoComprado.producto}</td>
                  <td>{productoComprado.precio}</td>
                  <td>{productoComprado.cantidad}</td>
                  <td>
                    {productoComprado.venta ? <Link to={`venta/${productoComprado.venta.id}`}>{productoComprado.venta.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${productoComprado.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${productoComprado.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${productoComprado.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
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
              <Translate contentKey="carroDeComprasApp.productoComprado.home.notFound">No Producto Comprados found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ProductoComprado;
