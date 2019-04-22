import React , {Component} from 'react'
import {connect} from 'react-redux'
import LoadText from '../containers/LoadText'
import HeaderBar from './HeaderBar'
import StatefulEditor from '../containers/StatefulEditor'
import LoadPreview from '../containers/LoadPreview'

const App = () => (
      <div>
        <HeaderBar />
        <StatefulEditor />
        <LoadPreview />
      </div>
    );

export default App

