import gym.envs as envs
import gym
env = gym.make('Boxing-v4')
env.reset()  #重置环境
for _ in range(1000):  #1000帧
    env.render()  #每一帧重新渲染环境
    env.step(env.action_space.sample()) # take a random action

# for _ in envs.registry.all():
#     print(_)