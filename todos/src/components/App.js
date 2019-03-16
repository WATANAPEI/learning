import React from 'react'
import LoadText from '../containers/LoadText'
import NewQuote from '../containers/NewQuote'
import TweetQuote from './TweetQuote'
import HeaderBar from './HeaderBar'

const App = () => (
  <div id="quote-box">
    <HeaderBar />
    <LoadText />
    <NewQuote />
    <TweetQuote />
  </div>
)

export default App
