import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './producto-seleccionado.reducer';
import { IProductoSeleccionado } from 'app/shared/model/microCarroDeCompras/producto-seleccionado.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ProductoSeleccionado = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const productoSeleccionadoList = useAppSelector(state => state.productoSeleccionado.entities);
  const loading = useAppSelector(state => state.productoSeleccionado.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="producto-seleccionado-heading" data-cy="ProductoSeleccionadoHeading">
        <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.home.title">Producto Seleccionados</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.home.createLabel">
              Create new Producto Seleccionado
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {productoSeleccionadoList && productoSeleccionadoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.producto">Producto</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.precio">Precio</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.cantidad">Cantidad</Translate>
                </th>
                <th>
                  <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.carro">Carro</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productoSeleccionadoList.map((productoSeleccionado, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${productoSeleccionado.id}`} color="link" size="sm">
                      {productoSeleccionado.id}
                    </Button>
                  </td>
                  <td>{productoSeleccionado.producto}</td>
                  <td>{productoSeleccionado.precio}</td>
                  <td>{productoSeleccionado.cantidad}</td>
                  <td>
                    {productoSeleccionado.carro ? (
                      <Link to={`carro-compra/${productoSeleccionado.carro.id}`}>{productoSeleccionado.carro.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${productoSeleccionado.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${productoSeleccionado.id}/edit`}
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
                        to={`${match.url}/${productoSeleccionado.id}/delete`}
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
              <Translate contentKey="gatewayApp.microCarroDeComprasProductoSeleccionado.home.notFound">
                No Producto Seleccionados found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ProductoSeleccionado;
