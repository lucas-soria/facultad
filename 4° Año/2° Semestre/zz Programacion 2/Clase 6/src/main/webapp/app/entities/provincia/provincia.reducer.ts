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

import { IProvincia, defaultValue } from 'app/shared/model/provincia.model';

export const ACTION_TYPES = {
  FETCH_PROVINCIA_LIST: 'provincia/FETCH_PROVINCIA_LIST',
  FETCH_PROVINCIA: 'provincia/FETCH_PROVINCIA',
  CREATE_PROVINCIA: 'provincia/CREATE_PROVINCIA',
  UPDATE_PROVINCIA: 'provincia/UPDATE_PROVINCIA',
  PARTIAL_UPDATE_PROVINCIA: 'provincia/PARTIAL_UPDATE_PROVINCIA',
  DELETE_PROVINCIA: 'provincia/DELETE_PROVINCIA',
  RESET: 'provincia/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProvincia>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ProvinciaState = Readonly<typeof initialState>;

// Reducer

export default (state: ProvinciaState = initialState, action): ProvinciaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROVINCIA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROVINCIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROVINCIA):
    case REQUEST(ACTION_TYPES.UPDATE_PROVINCIA):
    case REQUEST(ACTION_TYPES.DELETE_PROVINCIA):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_PROVINCIA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROVINCIA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROVINCIA):
    case FAILURE(ACTION_TYPES.CREATE_PROVINCIA):
    case FAILURE(ACTION_TYPES.UPDATE_PROVINCIA):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_PROVINCIA):
    case FAILURE(ACTION_TYPES.DELETE_PROVINCIA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVINCIA_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PROVINCIA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROVINCIA):
    case SUCCESS(ACTION_TYPES.UPDATE_PROVINCIA):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_PROVINCIA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROVINCIA):
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

const apiUrl = 'api/provincias';

// Actions

export const getEntities: ICrudGetAllAction<IProvincia> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROVINCIA_LIST,
    payload: axios.get<IProvincia>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IProvincia> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROVINCIA,
    payload: axios.get<IProvincia>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProvincia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROVINCIA,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IProvincia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROVINCIA,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IProvincia> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_PROVINCIA,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProvincia> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROVINCIA,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
