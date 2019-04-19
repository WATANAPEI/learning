import {connect} from 'react-redux'
import Text from '../components/Text'

const mapStateToProps = (state) => {
    return {
    text: state.update.text,
    author: state.update.author,
    count: state.update.count,
    }
}


export default connect(
    mapStateToProps
)(Text)
