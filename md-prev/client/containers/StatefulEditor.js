import React, {Component} from 'react'
import {connect} from 'react-redux'
import {changeText} from '../actions/index'
import {withStyles} from '@material-ui/core/styles'
import TextField from '@material-ui/core/TextField'
import classNames from 'classnames'

const styles = themes => ( {
    Editor: {
        width: '100%',
        maxheight: '500px',
        margin: "50px auto",
        padding: "20px 0 20px",
        rowsMax: "5",
    }
});

class StatefulEditor extends Component {

    constructor(props){
        super(props);
        this.handleChange = this.handleChange.bind(this)
    };
    handleChange(e){
        this.props.dispatch(changeText(e.currentTarget.value));
    };

    render(){

    return (
        <TextField label="Editor"
         margin="normal" variant="filled"  className={this.props.classes.Editor} multiline value={this.props.text} id="editor" placeholder="input mark down"
        onChange={this.handleChange} />
    )
    }
}

const mapStateToProps = (state) => {
    return {
        text: state.change.text,
    }
}

export default withStyles(styles)(
    connect(mapStateToProps)(StatefulEditor)
)
