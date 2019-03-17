import React, {Component} from 'react'
import { connect } from 'react-redux'
import {loadNewData} from '../actions'
import Button from '@material-ui/core/Button'


class NewQuote extends Component{
  constructor(props){
      super(props);
  };

  //参考サイトからjsonファイルを取得する
//内容について確認したければブラウザで見ればよい
  testFetch(count){
    return fetch('https://jsonplaceholder.typicode.com/todos/' + count)
    }

    testDataCreate(count){
    return this.testFetch(count)
        .then(response => response.json())
        .then(data => Object.assign(data, {author: 'author_sample' + '_' + count})) // insert author property
    }

  render(){

    const {count, dispatch} = this.props;
    return(
    <div>
        <Button variant="contained" color="primary" id="new-quote" onClick={ e=>{
            //dispatch action
            this.testDataCreate(count)
            .then(data => {
                dispatch(loadNewData(data))
            })
        }
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