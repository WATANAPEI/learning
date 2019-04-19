import React, {Component} from 'react'
import changeText from '../actions/index'

class Editor extends Component {

    constructor(props){
        super(props)
    };

    componentDidUpdate(prevProps){
        if(prevProps.value !== this.props.value){
            this.setInputValue(this.props.value)
        }
    }

    setInputValue(val) {
    dispatch(changeText(val))
    }

    render(){

    return (
        <input ref={(input) => this.input = input} defaultValue={this.props.value} id="editor" placeholder="input mark down"/>
    )
    }
}

export default Editor
