import {connect} from 'react-redux'
import Text from '../components/Text'

const mapStateToProps = (state) => {
    return {
    text: state.update.text,
    author: state.update.author
    }
}


export default connect(
    mapStateToProps
)(Text)