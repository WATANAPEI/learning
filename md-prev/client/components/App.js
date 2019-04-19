import React , {Component} from 'react'
import {connect} from 'react-redux'
import LoadText from '../containers/LoadText'
import HeaderBar from './HeaderBar'
import Editor from './Editor'

class App extends Component {
  constructor(props){
      super(props)
  }
    render(){
        const {inputValue} = this.props
        return(
      <div id="quote-box">
            <eaderBar />
            <Editor text={inputValue} />
            <LoadText />
          </div>
    )
    }
}
const mapStateToProps = (state) => ({
    inputValue: state.text
})

export default connect(mapStateToProps)(App)
