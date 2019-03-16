import { combineReducers } from 'redux'
import todos from './todos'
import visibilityFilter from './visibilityFilter'
import update from './update'

export default combineReducers({
  todos,
  visibilityFilter,
  update
})
