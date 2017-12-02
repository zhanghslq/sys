import React from 'react';
import PageContainer from '../components/PageContainer';

const Message = React.createClass({
  propTypes: {
    event: React.PropTypes.object,
  },

  renderDetail() {
    const event = this.props.event;

    if (event) {
      const {
        created_at,
        type,
        repo,
        actor,
        } = event;
      return (
        <div>
          <a
            href={`https://github.com/${actor.login}`}
            target="_blank"
            className="msg-avatar"
          >
            <img
              src={actor.avatar_url}
              alt={actor.login}
              className="am-img-responsive am-circle am-img-thumbnail"
            />
            <strong>{actor.login}</strong>
          </a>
          <ul>
            <li><em>日期：</em>{created_at}</li>
            <li><em>项目：</em>
              <a
                href={`https://github.com/${repo.name}`}
                target="_blank"
              >
                {repo.name}
              </a>
            </li>
            <li><em>操作：</em>{type}</li>
          </ul>
        </div>
      );
    }

    return (
      <p>
        Oops, 无法获取消息...
        <br />
        Message ID: {this.props.params.id}
      </p>
    );
  },

  render() {
    return (
      <div
        className="adm-msg-body"
      >
        <div className="adm-msg-content">
          {this.renderDetail()}
        </div>
      </div>
    );
  }
});

export default Message;
