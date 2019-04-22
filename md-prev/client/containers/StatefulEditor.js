import React, {Component} from 'react'
import {connect} from 'react-redux'
import {changeText} from '../actions/index'

class StatefulEditor extends Component {

    constructor(props){
        super(props);
        this.handleChange = this.handleChange.bind(this)
    };
    handleChange(event: SyntheticKeyboardEvent<HTMLInputElement>){
        this.props.dispatch(changeText(event.currentTarget.value));
    };

    render(){

    return (
        <textarea value={this.props.text} id="editor" placeholder="input mark down"
        onChange={this.handleChange}/>
    )
    }
}

const mapStateToProps = (state) => {
    return {
        text: state.change.text,
    }
}

export default connect(mapStateToProps)(StatefulEditor);
