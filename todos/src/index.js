import React from 'react'
import { render } from 'react-dom'
import { createStore, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'
import { Provider } from 'react-redux'
import App from './components/App'
import rootReducer from './reducers'
import {loadNewData} from './actions'


var cdnLink = document.createElement('script');
cdnLink.setAttribute('src', 'https://cdn.freecodecamp.org/testable-projects-fcc/v1/bundle.js');
document.head.appendChild(cdnLink);


const store = createStore(rootReducer, applyMiddleware(thunk))


let text = {
  title: 'aaaaaaaaaaa',
  author: 'Isaac'
}

console.log(store.getState());
store.dispatch(loadNewData(text));
console.log(store.getState());

render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
)

text =  {
  title: 'bbbb',
  author: 'Kita'
}

console.log(store.getState());
store.dispatch(loadNewData(text));
console.log(store.getState());
