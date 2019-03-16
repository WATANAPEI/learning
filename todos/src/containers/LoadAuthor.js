import React from 'react'
import {connect} from 'react-redux'
import Author from '../components/Author'

function testFetch(){
   return fetch('https://jsonplaceholder.typicode.com/todos/1')
}


const LoadAuthor = () => {
    let tweetData = testFetch()
                        .then(response => {
                            return response.json();
                        });
    console.log(JSON.stringify(tweetData));
                        
}

const mapStateToProps = (state) => ({
    author: state.author
})

export default connect(
    mapStateToProps
)(Author)