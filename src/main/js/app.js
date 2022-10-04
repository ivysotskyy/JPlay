class App extends React.Componnent {

    constructor(props) {
        super(props);
        this.state = {
            audioTracks: []
        };
    }

    componentDidMount() {
        client({
          method: "GET", path: "/api/tracks"
        }).done(response => {
            this.setState({
                audioTracks: response.entity._embedded.audioTracks
            });
        });
    }

    render() {
        return (
            <TrackList audioTracks={this.state.audioTracks}/>
        );
    }

}

class TrackList extends React.Componnent {

}

class AudioTrack extends React.Componnent {

}