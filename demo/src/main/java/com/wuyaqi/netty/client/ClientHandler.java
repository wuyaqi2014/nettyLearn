package com.wuyaqi.netty.client;

import com.wuyaqi.netty.model.Command;
import org.jboss.netty.channel.*;

/**
 * @author yaqi.wu
 * 2019-11-17 12:51
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if(e.getMessage() instanceof Command) {
            Command command = (Command)e.getMessage();
            System.out.println("client端接收对象：" + command.getActionName());
            return;
        }
        System.out.println(e.getMessage());
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client端建立链接，发送对象");
        Command command = new Command();
        command.setActionName("Hello Server.");
        e.getChannel().write(command);
    }
}
