import React from 'react'
import { connect } from 'react-redux'
import {loadNewData} from '../actions'
import Button from '@material-ui/core/Button'

function testFetch(count){
    return fetch('https://jsonplaceholder.typicode.com/todos/' + count)
}

function testDataCreate(count){
    return testFetch(count)
        .then(response => response.json())
        .then(data => Object.assign(data, {author: 'author_sample' + '_' + count})) // insert author property
}

const NewQuote = ({count, dispatch}) => (
    <div>
        <Button variant="contained" color="primary" id="new-quote" onClick={ e=>{
            console.log(' new quote button was clicked!');
            testDataCreate(count)
            .then(json =>  console.log(json)
            )
            .catch(error => console.error(error)
            );

            //dispatch action
            testDataCreate(count)
            .then(data => {
                dispatch(loadNewData(data))
            })
        }
        }>New Quote</Button> 
    
    </div>
)

const mapStateToProps = (state) => {
    return {
    count: state.update.count
    }
}

export default connect(
    mapStateToProps
)(NewQuote)