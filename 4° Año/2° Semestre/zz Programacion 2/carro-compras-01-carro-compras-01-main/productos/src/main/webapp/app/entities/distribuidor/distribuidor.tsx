import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './distribuidor.reducer';
import { IDistribuidor } from 'app/shared/model/distribuidor.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Distribuidor = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const distribuidorList = useAppSelector(state => state.distribuidor.entities);
  const loading = useAppSelector(state => state.distribuidor.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="distribuidor-heading" data-cy="DistribuidorHeading">
        <Translate contentKey="productosApp.distribuidor.home.title">Distribuidors</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="productosApp.distribuidor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="productosApp.distribuidor.home.createLabel">Create new Distribuidor</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {distribuidorList && distribuidorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="productosApp.distribuidor.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="productosApp.distribuidor.nombre">Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="productosApp.distribuidor.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="productosApp.distribuidor.habilitado">Habilitado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {distribuidorList.map((distribuidor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${distribuidor.id}`} color="link" size="sm">
                      {distribuidor.id}
                    </Button>
                  </td>
                  <td>{distribuidor.nombre}</td>
                  <td>{distribuidor.descripcion}</td>
                  <td>{distribuidor.habilitado ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${distribuidor.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${distribuidor.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${distribuidor.id}/delete`}
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
              <Translate contentKey="productosApp.distribuidor.home.notFound">No Distribuidors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Distribuidor;
