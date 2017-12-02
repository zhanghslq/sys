import React from 'react';
import {
  Container,
} from './AMUIReact';

const year = new Date().getFullYear();

export const SiteFooter = React.createClass({
  render() {
    return (
      <footer className="ask-footer">
        <Container>
          <p>© {year} AllMobilize, Inc. Licensed under MIT license.
            Developed with WebStorm.</p>
        </Container>
      </footer>
    );
  },
});
