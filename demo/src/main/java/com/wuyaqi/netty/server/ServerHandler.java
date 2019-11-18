package com.wuyaqi.netty.server;

import com.wuyaqi.netty.model.Command;
import org.jboss.netty.channel.*;

/**
 * @author yaqi.wu
 * 2019-11-17 12:51
 */
public class ServerHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext context, ChannelStateEvent e) throws Exception {
        System.out.println("channel connected");
//        super.channelConnected(context, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext context, ChannelStateEvent e) throws Exception {
        System.out.println("channel disconnected");
//        super.channelDisconnected(context, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext context, MessageEvent e) {
        if(e.getMessage() instanceof Command) {
            Command command = (Command) e.getMessage();
            System.out.println("server端接受对象：" + command.getActionName());
        }

        Command command = new Command();
        command.setActionName("Hello Client");
        e.getChannel().write(command);
    }

    @Override
    public void writeRequested(ChannelHandlerContext context, MessageEvent e) throws Exception {
        System.out.println("write Requested");
        super.writeRequested(context, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, ExceptionEvent e) {
        System.out.println("exception Caught");
        e.getCause().printStackTrace();
        Channel ch = e.getChannel();
        ch.close();
    }
}
