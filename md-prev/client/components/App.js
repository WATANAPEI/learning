import React , {Component} from 'react'
import {connect} from 'react-redux'
import LoadText from '../containers/LoadText'
import HeaderBar from './HeaderBar'
import Editor from './Editor'
import Preview from './Preview'

class App extends Component {
  constructor(props){
      super(props)
  }
    render(){
        const {inputValue} = this.props
        return(
      <div>
        <HeaderBar />
        <Editor text={inputValue} />
        <Preview />
      </div>
    )
    }
}
const mapStateToProps = (state) => ({
    inputValue: state.text
})

export default connect(mapStateToProps)(App)
