import { combineReducers } from 'redux'
import update from './update'
import change from './change'

export default combineReducers({
  update,
  change
})
