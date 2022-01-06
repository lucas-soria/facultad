import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPais, defaultValue } from 'app/shared/model/pais.model';

export const ACTION_TYPES = {
  FETCH_PAIS_LIST: 'pais/FETCH_PAIS_LIST',
  FETCH_PAIS: 'pais/FETCH_PAIS',
  CREATE_PAIS: 'pais/CREATE_PAIS',
  UPDATE_PAIS: 'pais/UPDATE_PAIS',
  PARTIAL_UPDATE_PAIS: 'pais/PARTIAL_UPDATE_PAIS',
  DELETE_PAIS: 'pais/DELETE_PAIS',
  RESET: 'pais/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPais>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PaisState = Readonly<typeof initialState>;

// Reducer

export default (state: PaisState = initialState, action): PaisState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PAIS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PAIS):
    case REQUEST(ACTION_TYPES.UPDATE_PAIS):
    case REQUEST(ACTION_TYPES.DELETE_PAIS):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_PAIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PAIS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAIS):
    case FAILURE(ACTION_TYPES.CREATE_PAIS):
    case FAILURE(ACTION_TYPES.UPDATE_PAIS):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_PAIS):
    case FAILURE(ACTION_TYPES.DELETE_PAIS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAIS_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PAIS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAIS):
    case SUCCESS(ACTION_TYPES.UPDATE_PAIS):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_PAIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/pais';

// Actions

export const getEntities: ICrudGetAllAction<IPais> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PAIS_LIST,
    payload: axios.get<IPais>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPais> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAIS,
    payload: axios.get<IPais>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPais> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAIS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IPais> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAIS,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IPais> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_PAIS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPais> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAIS,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
