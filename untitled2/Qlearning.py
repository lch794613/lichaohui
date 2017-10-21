import numpy as np
import pandas as pd
import time

np.random.seed(2)

n_states = 20# one axise 6states
action = ["left", "right"]
epsilon = 0.9  # greed police
alpha = 0.1  # learning rate
Lambda = 0.9  # discount factor
Max_episodes = 20  # maximum episodes 回合数
fresh_time = 0.01  # fresh time for one step


def builde_q_table(n_states, action):
    table = pd.DataFrame(
        np.zeros((n_states, len(action))),
        columns=action,  # columns表示纵向的名称
    )  # np表示这个表格中的值
    # print(table)
    return table


def choose_action(state, q_table):  # 传入state 然后选择这个行的数据 返回action_name
    state_action = q_table.iloc[state, :]  # iloc,ix,loc 分别是下标，（名字下标），名字index
    if (np.random.uniform() > epsilon) or (state_action.all() == 0):
        action_name = np.random.choice(action)  # 如果数值是0或还有10%的概率进行随机选择
    else:
        action_name = state_action.argmax()  # 否则选择最大数
    return action_name


def get_env_feedback(s, a):  # s=state a=action r=reward
    if a == "right":
        if s == n_states - 2:
            s_ = "terminal"
            r = 1
        else:
            s_ = s + 1
            r = 0
    else:
        r = 0
        if s == 0:
            s_ = s
        else:
            s_ = s - 1
    return s_, r


def update_env(s, episode, step_counter):
    env_list = ["-"] * (n_states - 1) + ["T"]  # ----------T
    if s == "terminal":  # 如果s到终点了打印出局数加步数
        interaction = 'Episode %s：total_steps=%s' % (episode + 1, step_counter)
        print('\r{}'.format(interaction), end='')
        time.sleep(2)
        print('\r                      ', end='')
    else:  # 如果没有到终点 将o加入到状态中间来
        env_list[s] = "o"  # s存在变化 更新list的状态
        interaction = ''.join(env_list)  # 将list转化为string 方便打印
        print('\r{}'.format(interaction), end='')
        time.sleep(fresh_time)


def control_loop():
    q_table = builde_q_table(n_states, action)
    for episode in range(Max_episodes):
        step_counter = 0
        s = 0
        is_terminated = False
        update_env(s, episode, step_counter)  # 奖励的反馈更新
        print(q_table)
        time.sleep(5)
        while not is_terminated:
            a = choose_action(s, q_table)  # 选择动作
            s_, r = get_env_feedback(s, a)  # 得到反馈 s_下一个状态以及奖励r
            q_predict = q_table.ix[s, a]
            if s_ != "terminal":
                q_target = r + Lambda * q_table.iloc[s_, :].max()#lambda莫凡比喻成眼镜 下一步的奖励*0。9
            else:
                q_target = r
                is_terminated = True

            q_table.ix[s, a] += alpha * (q_target - q_predict)#如果预测和真是的一样 则不更新 alpha为学习率0。1
            s = s_

            update_env(s, episode, step_counter + 1)
            step_counter += 1

    return q_table


if __name__ == '__main__':
    q_table = control_loop()
    print(q_table)
