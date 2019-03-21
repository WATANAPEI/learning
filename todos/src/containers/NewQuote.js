import React, {Component} from 'react'
import { connect } from 'react-redux'
import {updateData} from '../actions/index'
import Button from '@material-ui/core/Button'

class NewQuote extends Component{
  constructor(props){
      super(props);
  };

  render(){
    const {count, dispatch} = this.props;
    return(
    <div>
        <Button variant="contained" color="primary" id="new-quote"
            onClick={ e=>{ dispatch(updateData(count))}
            }>New Quote</Button> 
    </div>
    )

    }
}
const mapStateToProps = (state) => {
    return {
    count: state.update.count
    }
}

export default connect(
    mapStateToProps
)(NewQuote)